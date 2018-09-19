package cn.projectivity.gizwits.openapi;

import com.gizwits.openapi.sdk.DeviceInfo;
import com.gizwits.openapi.sdk.GizwitsException;
import com.gizwits.openapi.sdk.OpenApi;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OpenApiTest
{
    private final String wechatOpenId = "o4s0t1W9-sZ8cN3UHATL5OjZwLdo";
    private final String gizwitsAppId = "b435696d0d584861bef43772242e6494";
    private final String gizwitsProductKey = "5ed794bcf1d54595931500426b55d347";
    private final String gizwitsProductSecret = "1e86afbaf1024fbb97903abd0d32c032";
    private final String wechatDeviceId = "60-57-18-E6-9C-1D";
    private final String gizwitsDid = "YTQBRV6NJNvgawUL6JoWju";
    private final String deviceAlias = "JunitDevice";
    private final String deviceRemark = "";

    @Test
    public void testBindDeviceSuccess() throws GizwitsException
    {
        DeviceInfo deviceInfo = OpenApi.bindDevice(wechatOpenId, gizwitsAppId, gizwitsProductKey, gizwitsProductSecret, wechatDeviceId, deviceAlias, deviceRemark);
        Assert.assertEquals(gizwitsDid, deviceInfo.getDid());
        Assert.assertEquals(wechatDeviceId, deviceInfo.getMac());
        Assert.assertEquals(deviceAlias, deviceInfo.getAlias());
    }

    @Test(expected = GizwitsException.class)
    public void testBindDeviceFail() throws GizwitsException
    {
        String gizwitsAppId = "";
        OpenApi.bindDevice(wechatOpenId, gizwitsAppId, gizwitsProductKey, gizwitsProductSecret, wechatDeviceId, deviceAlias, deviceRemark);
    }

    @Test
    public void testGetBoundDevicesSuccess() throws GizwitsException
    {
        List<DeviceInfo> devices = OpenApi.getBoundDevices(wechatOpenId, gizwitsAppId);
        DeviceInfo device = devices.get(0);
        Assert.assertEquals(wechatDeviceId, device.getMac());
        Assert.assertEquals(deviceAlias, device.getAlias());
    }

    @Test
    public void testGetDeviceOnlineStatusSuccess() throws GizwitsException
    {
        DeviceInfo deviceInfo = OpenApi.bindDevice(wechatOpenId, gizwitsAppId, gizwitsProductKey, gizwitsProductSecret, wechatDeviceId, deviceAlias, deviceRemark);
        boolean onlineStatus = OpenApi.getDeviceOnlineStatus(wechatOpenId, gizwitsAppId, deviceInfo.getDid());
        Assert.assertFalse(onlineStatus);
    }

    @Test
    public void testUnbindDeviceSuccess() throws GizwitsException
    {
        boolean result = OpenApi.unbindDevice(wechatOpenId, gizwitsAppId, gizwitsDid);
        Assert.assertTrue(result);
    }

}
