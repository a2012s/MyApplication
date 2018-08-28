package com.example.wang.myapplication.player;

import java.io.File;
import java.lang.reflect.Method;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.util.Log;

/**
 * 
 * Created by Huxueqing on 2014/12/17
 * 
 */
public class MusicStorageManager {
    private final static String TAG = "MusicStorageManager";
    
    private static final String EXTERNAL_SD = "/external_sd";
    private static final String SPECIAL_SDCARD = "/sdcard1";
    private static final String PHONE_SDCARD = "/sdcard0";
    private static final String SDCARD = "/sdcard";
    private static final String OTG = "/otg";
    
    private static long MIN_STORAGE_SPACE = 5242880;
    
    private Method mMethodGetVolumePaths;

    private Method mMethodGetVolumeState;

    private Object mTarget;

    private static MusicStorageManager sInstance;

    public enum StorageType {
        UStorage, SDStorage, UsbStorage, NoStorage
    }

    public static MusicStorageManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (MusicStorageManager.class) {
                if (sInstance == null) {
                    sInstance = new MusicStorageManager(context);
                }
            }
        }
        return sInstance;
    }

    private MusicStorageManager(Context context) {
        StorageManager sm = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        Class<?> c = sm.getClass();
        try {
            mTarget = sm;
            mMethodGetVolumePaths = c.getDeclaredMethod("getVolumePaths");
            mMethodGetVolumeState = c.getDeclaredMethod("getVolumeState", String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getVolumePaths() {
        try {
            return (String[]) mMethodGetVolumePaths.invoke(mTarget);
        } catch (Exception e) {
            return new String[0];
        }
    }

    public String getVolumeState(String path) {
        try {
            return (String) mMethodGetVolumeState.invoke(mTarget, path);
        } catch (Exception e) {
            return Environment.MEDIA_REMOVED;
        }
    }

    public String getDiskPath(StorageType diskType) {
        String[] pathList = getVolumePaths();
        StorageType type;
        for (String path : pathList) {
            type = getStorageType(path);
            if (type == diskType) {
                return path;
            }
        }
        return null;
    }

    @SuppressLint("SdCardPath")
    public StorageType getStorageType(String path) {
        if (path.contains(EXTERNAL_SD) || path.contains(SPECIAL_SDCARD)) {
            return StorageType.SDStorage;
        }
        if (path.contains(PHONE_SDCARD) || path.contains(SDCARD)) {
            return StorageType.UStorage;
        }
        if (path.contains(OTG)) {
            return StorageType.UsbStorage;
        }
        return StorageType.UStorage;
    }

    /**
     * get the storage state of the phone
     * 
     * @param context
     * @return
     */
    private static String getPhoneStorageState(Context context) {
        if (sInstance == null) {
            sInstance = getInstance(context);
        }
        return sInstance.getVolumeState(sInstance.getDiskPath(StorageType.UStorage));
    }
    
    /**
     * get the directory path of the phone
     * 
     * @param context
     * @return
     */
    public static String getPhoneStorageDirect(Context context) {
        if (sInstance == null) {
            sInstance = getInstance(context);
        }
        return sInstance.getDiskPath(StorageType.UStorage);
    }
    
    /**
     * get the storage state of the sd card
     * 
     * @param context
     * @return
     */
    private static String getSDStorageState(Context context) {
        if (sInstance == null) {
            sInstance = getInstance(context);
        }
        return sInstance.getVolumeState(sInstance.getDiskPath(StorageType.SDStorage));
    }
    
    /**
     * get the directory path of the sd card
     * 
     * @param context
     * @return
     */
    public static String getSDStorageDirect(Context context) {
        if (sInstance == null) {
            sInstance = getInstance(context);
        }
        return sInstance.getDiskPath(StorageType.SDStorage);
    }
    
    /** check phone storage mounted
     * @param context
     * @return
     */
    public static boolean isPhoneStorageMounted(Context context) {
        return getPhoneStorageState(context).equals(Environment.MEDIA_MOUNTED);
    }

    /** check phone storage mounted
     * @param context
     * @return
     */
    public static boolean isSdStorageMounted(Context context) {
        return getSDStorageState(context).equals(Environment.MEDIA_MOUNTED);
    }
    
    public static double getFileSizebyPath(String path) {
        long size = 0;

        File file = new File(path);
        if (file.exists()) {
            size = file.length();
        }

        return size;
    }

    public static boolean isEnoughtSize(Context context, long size) {
        long totalSize = getSDAvailableSize(context);
        boolean result = (totalSize > size);
        
        if (!result) {
            totalSize = getAvailableSize(context);
            result = (totalSize > size);
        }
        return result;
    }
    
    
    
    /**
     * get available storage size of the phone
     * 
     * @param context
     * @return
     */
    public static long getAvailableSize(Context context) {
        if (!isPhoneStorageMounted(context)) {
            return -1;
        }
        try {
            StatFs stat = new StatFs(getPhoneStorageDirect(context));
            long blkSize;
            long availableBlocks;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blkSize = stat.getBlockSizeLong();
                availableBlocks =  stat.getAvailableBlocksLong();
            } else {
                blkSize = stat.getBlockSize();
                availableBlocks = stat.getAvailableBlocks();
            }
            return availableBlocks * blkSize;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Invalid path: /storage/sdcard0");
            return -1;
        }
    }

    /**
     * get available storage size of sdcard
     * 
     * @param context
     * @return
     */
    public static long getSDAvailableSize(Context context) {
        if (!getSDStorageState(context).equals(Environment.MEDIA_MOUNTED)) {
            return -1;
        }
        try {
            StatFs stat = new StatFs(getSDStorageDirect(context));
            long blkSize = stat.getBlockSizeLong();
            long availableBlocks = stat.getAvailableBlocksLong();
            return availableBlocks * blkSize;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Invalid path: /storage/sdcard1");
            return -1;
        }
    }

    /**
     * 
     * @param context
     * @return weather the phone storage space is less than MIN_STORAGE_SPACE
     */
    public static boolean phoneStorageLimit(Context context) {
        long size = getAvailableSize(context);
        // noinspection ConstantConditions
        return size != -1 && size < MIN_STORAGE_SPACE;
    }
}
