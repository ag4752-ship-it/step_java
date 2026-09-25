package session_seven_topics.class_problems;

public class SmartKitchenAssistant {

    public interface Washable {
        String clean();
    }

    public static abstract class KitchenTool {
        private int speedLevel;

        public KitchenTool() {
            this.speedLevel = 1;
        }

        public abstract String prepare();

        public int getSpeedLevel() {
            return this.speedLevel;
        }

        public void setSpeedLevel(int speedLevel) {
            if (speedLevel < 1 || speedLevel > 5) {
                return; // rejected, speed level stays unchanged
            }
            this.speedLevel = speedLevel;
        }
    }

    public static class Blender extends KitchenTool implements Washable {
        public Blender() {
            super();
        }

        @Override
        public String prepare() {
            return "Blending at speed " + getSpeedLevel();
        }

        @Override
        public String clean() {
            return "Blender rinsed and dried";
        }
    }

    public static void main(String[] args) {
        Blender b = new Blender();
        b.setSpeedLevel(3);
        System.out.println("Speed Level: " + b.getSpeedLevel());

        b.setSpeedLevel(9);
        System.out.println("After setting invalid speed (9), Speed Level: " + b.getSpeedLevel());

        System.out.println(b.prepare());
        System.out.println(b.clean());
    }
}
