package com.moesif.api.models;

public class BlockedByGovernanceRulesResponse {
    public boolean isBlocked;
    public String blockedBy;

    public EventResponseModel response;
    public static BlockedByGovernanceRulesResponse NO_BLOCK = new BlockedByGovernanceRulesResponse();

}
