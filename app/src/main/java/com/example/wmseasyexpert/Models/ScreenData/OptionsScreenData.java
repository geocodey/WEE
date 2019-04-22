package com.example.wmseasyexpert.Models.ScreenData;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OptionsScreenData extends BaseScreenData {
    private List<Option> options;
}
