package com.tasktracker;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Task {
    private int id;
    private String description;
    private String status;        //todo,in-progress,done

    @JsonFormat(pattern="yyyy-mm-dd   HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="yyyy-mm-dd  HH:mm:ss")
    private LocalDateTime updatedAt;
    public Task()
    {}
        public Task(int id,String description,String status)
        {
            this.id=id;
            this.description=description;
            this.status=status;
            this.createdAt=createdAt;
            this.updatedAt=updatedAt;
        }

        public int getId()
        {
            return id;
        }
        public void setId(int id)
        {
            this.id=id;
        }

        public String getDescription()
        {
            return description;
        }
        public void setDescription(String description)
        {
            this.description=description;
        }

        public String getStatus()
        {
            return status;
        }
        public void setStatus(String status)
        {
            this.status=status;
            this.updatedAt=LocalDateTime.now();
        }

        public LocalDateTime getCreatedAt()
        {
            return createdAt;
        }
        public LocalDateTime getUpdatedAt()
        {
            return updatedAt;
        }
    }


