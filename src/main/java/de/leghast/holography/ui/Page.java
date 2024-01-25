package de.leghast.holography.ui;

public enum Page {

    POSITION("§eAdjust the holograms position"),
    SIZE("§eAdjust the holograms size"),
    ROTATION("§eAdjust the holograms rotation"),
    ATTRIBUTES("§eAdjust the holograms attributes");

    private String title;

    Page(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

}
