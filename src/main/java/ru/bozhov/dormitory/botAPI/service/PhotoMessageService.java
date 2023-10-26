package ru.bozhov.dormitory.botAPI.service;

import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.io.File;

public interface PhotoMessageService {
    File savePhoto(org.telegram.telegrambots.meta.api.objects.File file, PhotoSize photoSize, String botToken);
}
