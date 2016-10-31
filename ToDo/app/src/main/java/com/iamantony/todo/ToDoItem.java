package com.iamantony.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDoItem {
    String m_title;
    String m_content;
    Date m_creationDate;

    public ToDoItem() {
        this("", "", new Date(java.lang.System.currentTimeMillis()));
    }

    public ToDoItem(String title) {
        this(title, "", new Date(java.lang.System.currentTimeMillis()));
    }

    public ToDoItem(String title, String content) {
        this(title, content, new Date(java.lang.System.currentTimeMillis()));
    }

    public ToDoItem(String title, String content, Date date) {
        m_title = title;
        m_content = content;
        m_creationDate = date;
    }

    public String getTitle() {
        return m_title;
    }

    public String getContent() {
        return m_content;
    }

    public Date getCreationDate() {
        return m_creationDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String dateStr = sdf.format(m_creationDate);
        return "(" + dateStr + ") " + m_title + ": " + m_content;
    }
}
