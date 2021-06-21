package pers.bc.utils.useragentutils;
/**
 * Enum constants classifying the different types of rendering engines which are being used by browsers.
 * 
 * @qualiFild pers.bc.utils.useragentutils.RenderingEngine.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2020-10-22<br>
 * @version 1.0<br>
 */
public enum RenderingEngine
{
    
    /**
     * Trident is the the Microsoft layout engine, mainly used by Internet Explorer.
     */
    TRIDENT("Trident"),
    /**
     * HTML parsing and rendering engine of Microsoft Office Word, used by some other products of the Office
     * suite instead of Trident.
     */
    WORD("Microsoft Office Word"),
    /**
     * Open source and cross platform layout engine, used by Firefox and many other browsers.
     */
    GECKO("Gecko"),
    /**
     * Layout engine based on KHTML, used by Safari, Chrome and some other browsers.
     */
    WEBKIT("WebKit"),
    /**
     * Proprietary layout engine by Opera Software ASA
     */
    PRESTO("Presto"),
    /**
     * Original layout engine of the Mozilla browser and related products. Predecessor of Gecko.
     */
    MOZILLA("Mozilla"),
    /**
     * Layout engine of the KDE project
     */
    KHTML("KHTML"),
    /**
     * Other or unknown layout engine.
     */
    OTHER("Other");
    
    String name;
    
    private RenderingEngine(String name)
    {
        this.name = name;
    }
    
}
