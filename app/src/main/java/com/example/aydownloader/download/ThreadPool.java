package com.example.aydownloader.download;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ThreadPool {
    private static final int DEFAULT_CORE_POOL_SIZE = 3;
    private static final int DEFAULT_MAX_POOL_SIZE = 20;
    private static final long DEFAULT_MAX_ALIVE_TIME = 10L;
    private int CORE_POOL_SIZE = DEFAULT_CORE_POOL_SIZE;
    private int MAX_POOL_SIZE = DEFAULT_MAX_POOL_SIZE;
    private long MAX_ALIVE_TIME = DEFAULT_MAX_ALIVE_TIME;
    private ThreadPoolExecutor mExecutor;

    private static ThreadPool instance;

    private ThreadFactory mThreadFactory = new ThreadFactory() {
        private final AtomicInteger id = new AtomicInteger();
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "download_task_no." + id.getAndIncrement());
        }
    };

    private ThreadPool(){

    }

    static ThreadPool getInstance() {
        if (instance == null){
            synchronized (ThreadPool.class) {
                if (instance == null)
                    instance = new ThreadPool();
            }
        }
        return instance;
    }

    void setCORE_POOL_SIZE(int CORE_POOL_SIZE) {
        this.CORE_POOL_SIZE = CORE_POOL_SIZE <= 0 ? DEFAULT_CORE_POOL_SIZE : CORE_POOL_SIZE;
    }

    void setMAX_POOL_SIZE(int MAX_POOL_SIZE) {
        this.MAX_POOL_SIZE = MAX_POOL_SIZE <= 0 ? DEFAULT_MAX_POOL_SIZE : MAX_POOL_SIZE;
    }

    void setMAX_ALIVE_TIME(long MAX_ALIVE_TIME) {
        this.MAX_ALIVE_TIME = MAX_ALIVE_TIME <= 0 ? DEFAULT_MAX_ALIVE_TIME : MAX_ALIVE_TIME;
    }

    void initExecutor() {
        if (mExecutor == null) {
            mExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                    MAX_POOL_SIZE,
                    MAX_ALIVE_TIME,
                    TimeUnit.SECONDS,
                    new LinkedBlockingDeque<Runnable>(),
                    mThreadFactory);
        }
    }

    ThreadPoolExecutor getExecutor(){
        if (mExecutor == null){
            initExecutor();
        }
        return mExecutor;
    }
}
