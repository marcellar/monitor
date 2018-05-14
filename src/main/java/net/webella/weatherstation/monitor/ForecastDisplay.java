package net.webella.weatherstation.monitor;

import java.util.Observable;
import java.util.Observer;

public class ForecastDisplay implements Observer, DisplayElement {

	private Observable observable;
	private float currentPressure = 29.92f;  
	private float lastPressure;
	
	public ForecastDisplay(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}

	public void display() {
		
		System.out.print("Forecast: ");
		if (currentPressure > lastPressure) {
			System.out.println("Improving weather on the way!");
		} else if (currentPressure == lastPressure) {
			System.out.println("More of the same");
		} else if (currentPressure < lastPressure) {
			System.out.println("Watch out for cooler, rainy weather");
		}
	}

	public void update(Observable obs, Object arg) {
		
		if (obs instanceof WeatherData) {
			lastPressure = currentPressure;
			WeatherData weatherData = (WeatherData)obs;
			currentPressure = weatherData.getPressure();
			display();
		}

	}

}
