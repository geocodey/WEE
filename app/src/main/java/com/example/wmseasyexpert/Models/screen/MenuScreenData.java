package com.example.wmseasyexpert.models.screen;

import com.example.wmseasyexpert.menu.MenuItem;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true)
public class MenuScreenData extends BaseScreenData implements Serializable {
    private MenuItem mainNode;
}
