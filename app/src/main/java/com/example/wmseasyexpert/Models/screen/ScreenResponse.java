package com.example.wmseasyexpert.models.screen;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScreenResponse {
    private int screenId;
    private String type;
    private String result;
}
