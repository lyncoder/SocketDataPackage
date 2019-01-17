package com.nxs.dataPackage.model;

import java.util.Arrays;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/1/17 16:59
 * @Version: 1.0
 */
public class Request {
    private short module;//请求模块
    private short cmd;//命令号
    private byte[] data;//数据部分

    public Request() {
    }

    public short getModule() {
        return module;
    }

    public void setModule(short module) {
        this.module = module;
    }

    public short getCmd() {
        return cmd;
    }

    public void setCmd(short cmd) {
        this.cmd = cmd;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getDataLength(){
        if(data == null){
            return 0;
        }
        return data.length;
    }

    @Override
    public String toString() {
        return "Request{" +
                "module=" + module +
                ", cmd=" + cmd +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
