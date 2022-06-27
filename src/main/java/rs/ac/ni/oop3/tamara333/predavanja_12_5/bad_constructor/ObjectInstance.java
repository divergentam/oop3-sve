package rs.ac.ni.oop3.tamara333.predavanja_12_5.bad_constructor;

import lombok.Setter;

import java.util.List;

public class ObjectInstance {

    private String label;
    private String otherLabel = "BAD_LABEL";

    public ObjectInstance(final String label, final List<ObjectInstance> currentInstance){
        synchronized (this){
            this.label = "Instance_ " + label;
            currentInstance.add(this);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            otherLabel = "OTHER: " + this.label;
        }
    }

    public synchronized String getLabel() {
        return label;
    }

    public synchronized String getOtherLabel() {
        return otherLabel;
    }
}
