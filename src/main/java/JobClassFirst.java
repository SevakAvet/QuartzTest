import org.quartz.*;

public class JobClassFirst implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("First class");
    }
}
