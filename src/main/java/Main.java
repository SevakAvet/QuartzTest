import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Copyright 2014 Plain Solutions
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Author: Avetisyan Sevak
 * Date: 26.04.14.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();


            JobDetail jobFirst = newJob(JobClassFirst.class)
                    .withIdentity("job1", "group1")
                    .build();

            JobDetail jobSecond = newJob(JobClassSecond.class)
                    .withIdentity("jobSecond", "group2")
                    .build();

            Trigger triggerFirst = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(
                            simpleSchedule()
                                    .withIntervalInMilliseconds(1000)
                                    .repeatForever()
                    )
                    .build();

            Trigger triggerSecond = newTrigger()
                    .withIdentity("trigger2", "group2")
                    .startNow()
                    .withSchedule(
                            simpleSchedule()
                                    .withIntervalInMilliseconds(5000)
                                    .repeatForever()
                    )
                    .build();

            scheduler.scheduleJob(jobFirst, triggerFirst);
            scheduler.scheduleJob(jobSecond, triggerSecond);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
