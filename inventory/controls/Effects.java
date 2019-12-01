/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.controls;

import javafx.scene.effect.Light.*;
import javafx.scene.paint.Color;
import javafx.scene.effect.*;

/**
 * @author Ollie
 */

/**
 * Effects has several STATIC effect method that can be called anywhere within the project
 * Making it Easier and convenient to create cool effects for any javaFx object,
 */
public class Effects {

    /**
     * Glow Effects makes the color of the object bright controlled by
     *
     * @param level controls the intensity
     * @return {@link javafx.scene.effect.Glow}
     */
    public static Glow GLOW(double level) {
        return new Glow(level);
    }

    /**
     * Glow Effect Default values
     * <b>level: 1.0</b>
     *
     * @return {@link javafx.scene.effect.Glow}
     */
    public static Glow GLOW() {
        return new Glow(1.0);
    }

    /**
     * Bloom Effects makes the object appear softer controlled by
     *
     * @param level controls the intensity
     * @return {@link javafx.scene.effect.Bloom}
     */


    public static Bloom BLOOM(double level) {
        return new Bloom(level);
    }

    /**
     * Bloom Effect Default values
     * <b>level: 1.0</b>
     *
     * @return {@link javafx.scene.effect.Bloom}
     */
    public static Bloom BLOOM() {
        return new Bloom(1.0);
    }


    /**
     * BoxBlur makes the  object blur
     *
     * @param height    and
     * @param width     control the size of the blur
     * @param iteration controls strength
     * @return {@link javafx.scene.effect.BoxBlur}
     */
    public static BoxBlur BOX_BLUR(int height, int width, int iteration) {
        return new BoxBlur(height, width, iteration);
    }

    /**
     * BoxBlur Effect Default values
     * <b>height: 5</b>
     * <b>| width: 5</b>
     * <b>| iteration:3</b>
     *
     * @return {@link javafx.scene.effect.BoxBlur}
     */
    public static BoxBlur BOX_BLUR() {
        return new BoxBlur(5, 5, 3);
    }


    /**
     * MotionBlur makes the  object blur
     *
     * @param r controls the radius
     * @param a control the size of the blur
     * @return {@link javafx.scene.effect.MotionBlur}
     */
    public static MotionBlur MOTION_BLUR(float r, float a) {
        return new MotionBlur(r, a);
    }

    /**
     * MotionBlur Effect Default values
     * <b>radius: 15.0f</b>
     * <b>| area: 45.0f</b>
     *
     * @return {@link javafx.scene.effect.MotionBlur}
     */
    public static MotionBlur MOTION_BLUR() {
        return new MotionBlur(15.0f, 45.0f);
    }


    /**
     * Gaussian Blur creates a Gaussain blur
     *
     * @return {@link javafx.scene.effect.GaussianBlur}
     */
    public static GaussianBlur GAUSSIAN_BLUR() {
        return new GaussianBlur();
    }

    /**
     * Drop Shadow add a shadow to the object
     *
     * @param dx    controls the horizonal displacement
     * @param dy    controls the vertical displacement
     * @param color controls Color
     * @return {@link javafx.scene.effect.DropShadow}
     */


    public static DropShadow DROP_SHADOW(double dx, double dy, Color color) {
        DropShadow ds = new DropShadow();
        ds.setColor(color);
        ds.setOffsetX(dx);
        ds.setOffsetY(dy);
        return ds;
    }

    /**
     * DropShadow Effect Default values
     * <b>OffsetX: 4.0f</b>
     * <b>| OffsetY:4.0f</b>
     * <b>| Color: Coral</b>
     *
     * @return {@link javafx.scene.effect.DropShadow}
     */
    public static DropShadow DROP_SHADOW() {
        DropShadow ds = new DropShadow();
        ds.setColor(Color.BLACK);
        ds.setOffsetX(2.0f);
        ds.setOffsetY(0.5f);
        return ds;
    }


    /**
     * Inner Shadow add a shadow to the object's inside
     *
     * @param dx    controls the horizonal displacement
     * @param dy    controls the vertical displacement
     * @param color controls Color
     * @param r     controls the radius
     * @return {@link javafx.scene.effect.InnerShadow}
     */
    public static InnerShadow Inner_Shadow(double r, double dx, double dy, Color color) {
        return new InnerShadow(r, dx, dy, color);
    }

    /**
     * InnerShadow Effect Default values
     * <b>OffsetX: 2.0f</b>
     * <b>| OffsetY:2.0f</b>
     *
     * @return {@link javafx.scene.effect.InnerShadow}
     */

    public static InnerShadow Inner_Shadow() {
        InnerShadow is = new InnerShadow();
        is.setOffsetX(2.0f);
        is.setOffsetY(2.0f);
        return is;
    }


    /**
     * Reflection add a Glass-like Reflection to the object
     *
     * @param fraction controls the portion to be reflected
     * @return {@link javafx.scene.effect.Reflection}
     */
    public static Reflection REFLECTION(double fraction) {
        Reflection ref = new Reflection();
        ref.setFraction(fraction);
        return ref;
    }

    /**
     * Reflection Effect Default values
     * <b>Fraction: 0.9</b>
     *
     * @return {@link javafx.scene.effect.Reflection}
     */
    public static Reflection REFLECTION() {
        Reflection ref = new Reflection();
        ref.setFraction(0.9);
        return ref;
    }

    /**
     * add a Light source to the object creating a casting shadow
     *
     * @param distance    controls how far or near the source is
     * @param scaleFactor Controls the size of the source
     * @return {@link javafx.scene.effect.Lighting}
     */
    public static Lighting LIGHTING(double distance, double scaleFactor) {

        Distant light = new Distant();
        light.setAzimuth(distance);
        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(scaleFactor);
        return lighting;
    }

    /**
     * Lighting Effect Default values
     * <b>distance: -135.0f</b>
     * <b>| scaleFactor:5.0f</b>
     *
     * @return {@link javafx.scene.effect.Lighting}
     */
    public static Lighting LIGHTING() {

        Distant light = new Distant();
        light.setAzimuth(-135.0f);
        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0f);
        return lighting;
    }


}
