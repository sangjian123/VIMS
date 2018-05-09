package com.vms.utils;

public enum KfaTopic
{
    opcsTopic ("opcs_topic"), curBalAlertTopic ("curBalAlert_topic"), curBalStopTopic ("curBalStop_topic"), balInfoTopic ("balInfo_topic"), alertTopic (
        "alert_topic"), gatherTopic ("gather_topic"), veeDTopic ("vee_daily_topic"), veeMTopic (
            "vee_monthly_topic"), veeNoDataTopic ("vee_dailyNoData_topic"), veeMNoDataTopic ("vee_MNoData_topic"), veeDTopicG (
                "vee_daily_topic_g"), veeNoDataTopicG (
                    "vee_dailyNoData_topic_g"), veeMTopicG ("vee_monthly_topic_g"), veeMNoDataTopicG ("vee_MNoData_topic_g");
    
    private String topic;
    
    public String value()
    {
        return topic;
    }
    
    private KfaTopic(String topic)
    {
        this.topic = topic;
    }
}
