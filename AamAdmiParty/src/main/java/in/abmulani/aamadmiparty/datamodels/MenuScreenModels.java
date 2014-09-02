package in.abmulani.aamadmiparty.datamodels;

import android.graphics.drawable.Drawable;

import in.abmulani.aamadmiparty.utils.LITERALS.CATEGORY;

/**
 * Created by AABID on 17/3/14.
 */
public class MenuScreenModels {

    private String name;
    private int image;
    private CATEGORY category;
    private Drawable color;

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public CATEGORY getCategory() {
        return category;
    }

    public Drawable getColor() {
        return color;
    }

    public MenuScreenModels(String name, int image, CATEGORY category, Drawable color) {
        this.name = name;
        this.image = image;
        this.category = category;
        this.color = color;
    }
}
