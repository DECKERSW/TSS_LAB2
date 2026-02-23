/*    */ import java.awt.geom.GeneralPath;
/*    */ 
/*    */ public class ShapeFactory {
/*    */   public Shape shape;
/*    */   
/*    */   public ShapeFactory(int shape_type) {
/*    */     GeneralPath path;
/*    */     double tmp_height;
/*  9 */     this.width = 25;
/* 10 */     this.height = 25;
/*    */ 
/*    */     
/* 13 */     this.stroke = new BasicStroke(3.0F);
/* 14 */     switch (shape_type / 10) {
/*    */       case 1:
/* 16 */         this.shape = createStar(3, new Point(0, 0), this.width / 2.0D, this.width / 2.0D);
/*    */         break;
/*    */       case 3:
/* 19 */         this.shape = createStar(5, new Point(0, 0), this.width / 2.0D, this.width / 4.0D);
/*    */         break;
/*    */       case 5:
/* 22 */         this.shape = new Rectangle2D.Double(-this.width / 2.0D, -this.height / 2.0D, this.width, this.height);
/*    */         break;
/*    */       case 7:
/* 25 */         path = new GeneralPath();
/* 26 */         tmp_height = Math.sqrt(2.0D) / 2.0D * this.height;
/* 27 */         path.moveTo((-this.width / 2), -tmp_height);
/* 28 */         path.lineTo(0.0D, -tmp_height);
/* 29 */         path.lineTo((this.width / 2), tmp_height);
/* 30 */         path.closePath();
/* 31 */         this.shape = path;
/*    */         break;
/*    */       case 9:
/* 34 */         this.shape = new Arc2D.Double(-this.width / 2.0D, -this.height / 2.0D, this.width, this.height, 30.0D, 300.0D, 
/* 35 */             2);
/*    */         break;
/*    */       
/*    */       default:
/* 39 */         throw new Error("type is nusupported");
/*    */     } 
/* 41 */     switch (shape_type % 10) { case 1:
/* 42 */         this.stroke = new BasicStroke(3.0F);
/*    */       case 3:
/*    */         return;
/*    */       case 4:
/* 46 */         this.stroke = new BasicStroke(7.0F);
/*    */       
/*    */       case 7:
/* 49 */         this.paint = new GradientPaint(
/* 50 */             -this.width, -this.height, Color.white, 
/* 51 */             this.width, this.height, Color.gray, true);
/*    */       
/*    */       case 8:
/* 54 */         this.paint = Color.red; }
/*    */ 
/*    */     
/* 57 */     throw new Error("type is nusupported");
/*    */   }
/*    */   public BasicStroke stroke; public Paint paint; public int width;
/*    */   public int height;
/*    */   
/*    */   private static Shape createStar(int arms, Point center, double rOuter, double rInner) {
/* 63 */     double angle = Math.PI / arms;
/*    */     
/* 65 */     GeneralPath path = new GeneralPath();
/*    */     
/* 67 */     for (int i = 0; i < 2 * arms; i++) {
/*    */       
/* 69 */       double r = ((i & 0x1) == 0) ? rOuter : rInner;
/* 70 */       Point2D.Double p = new Point2D.Double(center.x + Math.cos(i * angle) * r, center.y + Math.sin(i * angle) * r);
/* 71 */       if (i == 0) { path.moveTo(p.getX(), p.getY()); }
/* 72 */       else { path.lineTo(p.getX(), p.getY()); }
/*    */     
/* 74 */     }  path.closePath();
/* 75 */     return path;
/*    */   }
/*    */ }


/* Location:              C:\Users\mihak\OneDrive\Desktop\lab3.jar!\ShapeFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */