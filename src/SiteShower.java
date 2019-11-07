import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SiteShower {
    public void getSite(String href) throws IOException, URISyntaxException {
        URI uri = new URI(href);
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
