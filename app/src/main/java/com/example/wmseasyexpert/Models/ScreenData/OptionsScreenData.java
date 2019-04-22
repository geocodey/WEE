package com.example.wmseasyexpert.Models.ScreenData;

import androidx.annotation.CallSuper;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true)
public class OptionsScreenData extends BaseScreenData {
    private List<Option> options;
}
