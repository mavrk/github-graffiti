import java.awt.Color;

public enum MyColor {
   LIGHT_GRAY(new Color(242, 237, 242), "Light Gray", 0), LIGHT_GREEN(new Color(198, 228, 139), "Light Green", 1), 
   GREEN(new Color(123, 201, 111), "GREEN", 2), DARK_GREEN(new Color(35, 154, 59), "DARK GREEN", 3), DARKEST_GREEN(new Color(25, 97, 39), "DARK GREEN", 4);
   private Color color;
   private String name;
   private int shortName;

   private MyColor(Color color, String name, int shortName) {
      this.color = color;
      this.name = name;
      this.shortName = shortName;
   }

   public MyColor next() {
      int index = 0;
      for (int i = 0; i < MyColor.values().length; i++) {
         if (MyColor.values()[i] == this) {
            index = (i + 1) % MyColor.values().length;
         }
      }
      return MyColor.values()[index];
   }

   public Color getColor() {
      return color;
   }

   public String getName() {
      return name;
   }

   public int getShortName() {
      return shortName;
   }

   @Override
   public String toString() {
      return ""+shortName;
   }

}