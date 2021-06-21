package pers.bc.utils.useragentutils;

/**
 * Enum contact for the type of used device
 * 
 * @qualiFild pers.bc.utils.useragentutils.DeviceType.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2020-10-22<br>
 * @version 1.0<br>
 */
public enum DeviceType
{
    
    /**
     * Standard desktop or laptop computer
     */
    COMPUTER("Computer"),
    /**
     * Mobile phone or similar small mobile device
     */
    MOBILE("Mobile"),
    /**
     * Small tablet type computer.
     */
    TABLET("Tablet"),
    /**
     * Game console like the Wii or Playstation.
     */
    GAME_CONSOLE("Game console"),
    /**
     * Digital media receiver like the Google TV.
     */
    DMR("Digital media receiver"),
    /**
     * Other or unknow type of device.
     */
    UNKNOWN("Unknown");
    
    String name;
    
    private DeviceType(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
}
