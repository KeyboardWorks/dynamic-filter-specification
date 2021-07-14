package keyboard.works.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

public class ResponseHelper {

public static <T, S> T createResponse(Class<T> targetClazz, S source) {
		
		T response = null;
		
		try {
			response = targetClazz.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		BeanUtils.copyProperties(source, response);
		
		return response;
	}
	
	public static <T, S> List<T> createResponses(Class<T> targetClazz, List<S> sources) {
		
		List<T> list = sources.stream()
			.map(source -> {
				return createResponse(targetClazz, source);
			})
			.collect(Collectors.toList());
		
		return list;
	}
	
}
