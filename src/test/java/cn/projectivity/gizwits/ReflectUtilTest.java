package cn.projectivity.gizwits;

import cn.projectivity.gizwits.common.beans.WxEventDeviceBind;
import cn.projectivity.gizwits.common.utils.ReflectUtil;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by matt on 16-5-26.
 */
public class ReflectUtilTest {

    @Test
    public void isSameType() throws Exception {
        assertTrue(ReflectUtil.isSameType(WxEventDeviceBind.class, "MsgType", String.class));
        assertTrue(ReflectUtil.isSameType(WxEventDeviceBind.class, "Event", String.class));
        assertFalse(ReflectUtil.isSameType(WxEventDeviceBind.class, "CreateTime", String.class));
    }

    @Test
    public void getFidld() throws Exception {
        assertNotNull(ReflectUtil.getFidld(WxEventDeviceBind.class, "MsgType"));
        assertNotNull(ReflectUtil.getFidld(WxEventDeviceBind.class, "Event"));
        assertNotNull(ReflectUtil.getFidld(WxEventDeviceBind.class, "CreateTime"));
//        assertNull(ReflectUtil.getFidld(WxEventDeviceBind.class, "abc"));
    }

}