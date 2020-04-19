package com.mic.demo.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class BlockHandler {

    public static String handlerException(int id,Throwable exception) {
        return "handlerException";
    }

    public static String handlerBlock(int id,BlockException exception) {
        return "handlerBlock";
    }

}
