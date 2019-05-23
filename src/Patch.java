/**
 * Patches store all the information about a ground,
 * which contains the daisy, temperature and how to
 * calculate the local temperature in each tick.
 */
public class Patch {
    //the temperature of this patch
    private double temperature;
    //the daisy on this patch
    private Daisy daisy;

    //calculate the temperature of this patch
    public void calTemp(double albedoOfSurface, double solarLuminosity) {
        // the absorbed luminosity by this patch
        double absorbedLuminosity = 0;
        double localHeating = 0;
        // if there is no daisy on this patch, the absorbed luminosity
        // is calculated by (1 - albedo of surface) * solar luminosity
        if (daisy == null) {
            absorbedLuminosity = (1 - Params.ALBEDO_Of_SURFACE) * solarLuminosity;
        }
        // else the absorbed luminosity is calculated by
        // (1 - albedo of daisy on this patch) * solar luminosity
        else {
            absorbedLuminosity = (1 - daisy.getAlbedo()) * solarLuminosity;
        }
        // calculate the local heating
        if (absorbedLuminosity > 0) {
            localHeating = 72 * Math.log(absorbedLuminosity) + 80;
        }
        else {
            localHeating = 80;
        }
        // set the temperature of this patch to the average
        // number of the current temperature and the local heating
        temperature = (temperature + localHeating) / 2;
    }

    public Daisy getDaisy() {
        return daisy;
    }

    public void setDaisy(Daisy daisy) {
        this.daisy = daisy;
    }

    public void dieDaisy() {
        this.daisy = null;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
