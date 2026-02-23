/*    */ import java.awt.Component;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.SwingUtilities;
/*    */ 
/*    */ public class TitlesFrame extends JFrame {
/*    */   public TitlesFrame() {
/*  7 */     initUI();
/*    */   }
/*    */ 
/*    */   
/*    */   private void initUI() {
/* 12 */     setTitle("Кривые фигуры");
/* 13 */     setDefaultCloseOperation(3);
/* 14 */     add(new TitlesPanel(78));
/* 15 */     setSize(350, 350);
/* 16 */     setLocationRelativeTo((Component)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 21 */     SwingUtilities.invokeLater(new Runnable()
/*    */         {
/*    */           public void run()
/*    */           {
/* 25 */             TitlesFrame ps = new TitlesFrame();
/* 26 */             ps.setVisible(true);
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\mihak\OneDrive\Desktop\lab3.jar!\TitlesFrame.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */