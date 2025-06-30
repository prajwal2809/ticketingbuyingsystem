package com.example.microservices.inventory.response;

public class VenueInventoryResponse {

    private Long venueId;
    private String venueName;
    private Long totalCapacity;

    public VenueInventoryResponse() {}

    public VenueInventoryResponse(Long venueId, String venueName, Long totalCapacity) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.totalCapacity = totalCapacity;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public Long getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Long totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    // ✅ Manual Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long venueId;
        private String venueName;
        private Long totalCapacity;

        public Builder venueId(Long venueId) {
            this.venueId = venueId;
            return this;
        }

        public Builder venueName(String venueName) {
            this.venueName = venueName;
            return this;
        }

        public Builder totalCapacity(Long totalCapacity) {
            this.totalCapacity = totalCapacity;
            return this;
        }

        public VenueInventoryResponse build() {
            return new VenueInventoryResponse(venueId, venueName, totalCapacity);
        }
    }
}
