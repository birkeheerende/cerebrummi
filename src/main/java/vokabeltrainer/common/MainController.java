package vokabeltrainer.common;

import vokabeltrainer.panels.MainView;

public class MainController implements MainControllerConnector
{
   private MainView mainView;
   
   public MainController()
   {
      mainView = new MainView();
   }

   public MainView getMainView()
   {
      return mainView;
   }

}
