package vokabeltrainer;

import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ComponentTitledBorder
      implements Border, MouseListener, MouseMotionListener, SwingConstants
{

   private int offset = 0;
   private Component comp;
   private JComponent container;
   private Rectangle rect;
   private Border border;
   private boolean mouseEntered = false;
   private int left;

   public ComponentTitledBorder(Component comp, JComponent container,
         Border border, int left)
   {
      this.comp = comp;
      this.container = container;
      this.border = border;
      this.left = left;
      container.addMouseListener(this);
      container.addMouseMotionListener(this);
   }

   @Override
   public boolean isBorderOpaque()
   {
      return true;
   }

   @Override
   public void paintBorder(Component c, Graphics g, int x, int y, int width,
         int height)
   {
      Insets borderInsets = border.getBorderInsets(c);
      Insets insets = getBorderInsets(c);
      int temp = (insets.top - borderInsets.top) / 2;
      border.paintBorder(c, g, x, y + temp, width, height - temp);
      Dimension size = comp.getPreferredSize();
      rect = new Rectangle(offset, 0, size.width, size.height);
      SwingUtilities.paintComponent(g, comp, (Container) c, rect);
   }

   @Override
   public Insets getBorderInsets(Component c)
   {
      Dimension size = comp.getPreferredSize();
      Insets insets = border.getBorderInsets(c);
      insets.top = Math.min(insets.top, size.height);
      insets.left = left;
      return insets;
   }

   private void dispatchEvent(MouseEvent me)
   {
      if (rect != null && rect.contains(me.getX(), me.getY()))
      {
         dispatchEvent(me, me.getID());
      }
   }

   @SuppressWarnings("deprecation")
private void dispatchEvent(MouseEvent me, int id)
   {
      Point pt = me.getPoint();
      pt.translate(-offset, 0);

      comp.setSize(rect.width, rect.height);
      comp.dispatchEvent(new MouseEvent(comp, id, me.getWhen(),
            me.getModifiers(), pt.x, pt.y, me.getClickCount(),
            me.isPopupTrigger(), me.getButton()));
      if (!comp.isValid())
      {
         container.repaint();
      }
   }

   @Override
   public void mouseClicked(MouseEvent me)
   {
      dispatchEvent(me);
   }

   @Override
   public void mouseEntered(MouseEvent me)
   {
   }

   @Override
   public void mouseExited(MouseEvent me)
   {
      if (mouseEntered)
      {
         mouseEntered = false;
         dispatchEvent(me, MouseEvent.MOUSE_EXITED);
      }
   }

   @Override
   public void mousePressed(MouseEvent me)
   {
      dispatchEvent(me);
   }

   @Override
   public void mouseReleased(MouseEvent me)
   {
      dispatchEvent(me);
   }

   @Override
   public void mouseDragged(MouseEvent e)
   {
   }

   @Override
   public void mouseMoved(MouseEvent me)
   {
      if (rect == null)
      {
         return;
      }

      if (mouseEntered == false && rect.contains(me.getX(), me.getY()))
      {
         mouseEntered = true;
         dispatchEvent(me, MouseEvent.MOUSE_ENTERED);
      }
      else if (mouseEntered == true)
      {
         if (rect.contains(me.getX(), me.getY()) == false)
         {
            mouseEntered = false;
            dispatchEvent(me, MouseEvent.MOUSE_EXITED);
         }
         else
         {
            dispatchEvent(me, MouseEvent.MOUSE_MOVED);
         }
      }
   }
}
