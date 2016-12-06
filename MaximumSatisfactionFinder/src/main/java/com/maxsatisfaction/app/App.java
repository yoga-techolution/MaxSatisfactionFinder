package com.maxsatisfaction.app;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.maxsatisfaction.app.App;
import com.maxsatisfaction.domain.InputItems;
import com.maxsatisfaction.service.SatisfactionService;
import com.maxsatisfaction.util.FileParsingUtility;
/**
 * 
 * @author Yoga Chandran
 * Main class 
 */

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.techolution")
public class App 
{
    public static void main( String[] args )throws IOException, Exception
    {
    	/**
    	 * Below code is used to load the beans from the spring context
    	 */
    	ApplicationContext context = SpringApplication.run(App.class);
    	FileParsingUtility fileParsingUtility = context.getBean(FileParsingUtility.class);
    	//This method reads the file stored in src/main/resources folder
    	InputItems inputItems = fileParsingUtility.readFile();
    	SatisfactionService satisfactionService = context.getBean(SatisfactionService.class);
    	satisfactionService.setInputItems(inputItems);
    	int totalSatisfactionCalcInMinutes = satisfactionService.calculateMaxSatisfactionInMinutes();
    	int totalSatisfactionCalcInSeconds = satisfactionService.calculateMaxSatisfactionInSeconds();
    	System.out.println("totalsatisfaction calculated in mins "+totalSatisfactionCalcInMinutes);
    	System.out.println("totalsatisfaction calculated in seconds "+totalSatisfactionCalcInSeconds);
    	
    }
}
