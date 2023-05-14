package com.komarov.projectnlo.model;

public class FailResolver {
    private String message;
    private Step state;

    public String getMessage() {
        initMessage();
        return message;
    }

    private void initMessage() {
        switch (state) {
            case GREETING -> setMessage("<h3>Ты отклонил вызов.</h3><h2>Поражение.</h2>");
            case BRIDGE -> setMessage("<h3>Ты не пошел на переговоры.</h3><h2>Поражение.</h2>");
            case CAPTAIN -> setMessage("<h3>Твою ложь разоблачили.</h3><h2>Поражение.</h2>");
            default -> throw new RuntimeException("It's impossible");
        }
    }

    public void setState(Step state) {
        this.state = state;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
