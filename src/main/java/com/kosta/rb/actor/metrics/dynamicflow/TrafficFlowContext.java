package com.kosta.rb.actor.metrics.dynamicflow;

import com.kosta.rb.actor.metrics.dynamicflow.abs.UniqueDynamicFlowContext;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Builder
@ToString
public class TrafficFlowContext extends UniqueDynamicFlowContext {
    public String startDir;
    public String endDir;
    public String type;
    public String json;
}
