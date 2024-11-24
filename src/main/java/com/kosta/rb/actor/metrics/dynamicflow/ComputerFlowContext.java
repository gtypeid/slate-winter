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
public class ComputerFlowContext extends UniqueDynamicFlowContext {
    private String serverType;
    private String ip;
    private String port;
    private String dirKey;
}
