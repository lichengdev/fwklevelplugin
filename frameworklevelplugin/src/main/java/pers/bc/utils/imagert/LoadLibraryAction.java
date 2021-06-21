package pers.bc.utils.imagert;

import java.security.PrivilegedAction;

public class LoadLibraryAction implements PrivilegedAction<Void>
{
    private String theLib;
    
    public LoadLibraryAction(String paramString)
    {
        this.theLib = paramString;
    }
    
    public Void run()
    {
        System.loadLibrary(this.theLib);
        return null;
    }
}
