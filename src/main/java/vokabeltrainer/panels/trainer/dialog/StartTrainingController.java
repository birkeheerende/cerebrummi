package vokabeltrainer.panels.trainer.dialog;

public class StartTrainingController implements StartTrainingControllerConnector
{
   private StartTrainingView startTrainingView;
   
   public StartTrainingController()
   {
      this.startTrainingView = new StartTrainingView(this);
   }

   public StartTrainingView getStartTrainingView()
   {
      return startTrainingView;
   }
}
