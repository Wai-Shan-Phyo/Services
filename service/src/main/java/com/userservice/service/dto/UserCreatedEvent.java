package com.userservice.service.dto;

public class UserCreatedEvent {
    private String eventId;
      private int userId;
      private String name ;



    private String email;
      private String role;
    public UserCreatedEvent(){

    }

    public UserCreatedEvent(String eventId,int id, String name, String mail, String role) {
        this.eventId=eventId;
        this.userId=id;
        this.name=name;
        this.email=mail;
        this.role=role;
    }
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

}
