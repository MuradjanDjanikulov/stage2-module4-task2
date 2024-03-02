package com.mjc.stage2.impl;


import com.mjc.stage2.Connection;

public class ProxyConnection implements Connection {
    private RealConnection realConnection;

    public ProxyConnection(RealConnection realConnection) {
        this.realConnection = realConnection;
    }

    @Override
    public void close() {
        // Return the connection to the ConnectionPool
        ConnectionPool.getInstance().releaseConnection(this);
    }

    public void reallyClose() {
        // Close the real connection
        if (realConnection != null && !realConnection.isClosed()) {
            realConnection.close();
        }
    }

    // For example, you might delegate methods like isClosed() to the real connection.
    @Override
    public boolean isClosed() {
        return realConnection == null || realConnection.isClosed();
    }

}
