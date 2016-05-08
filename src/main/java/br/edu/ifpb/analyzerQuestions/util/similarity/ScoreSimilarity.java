package br.edu.ifpb.analyzerQuestions.util.similarity;

import java.util.Map;

import br.edu.ifpb.analyzerQuestions.util.data.Questions;

/***
 * SCORE USANDO COSSENO [ score > 0.0]
 *
 */

public class ScoreSimilarity {

	private Map<String, Float> vector1;
	private Map<String, Float> vector2;

	/**
	 * CALCULA SIMILARIDADE DE COSSENO COM SEGUINTE EQUACAO
	 *  v1*v2 / sqrt(v1²) * sqrt(v2²)
	 *  
	 *  Disconsidera-se vetores que não possuam frequencia de tokens <= 1
	 */
	public Double getScoreSimilarity(Vector v1, Vector v2) {

		double Wn = 0.0;
		double w1 = 0.0;
		double w2 = 0.0;
		double Wd = 0.0;

		this.vector1 = v1.getVector();
		this.vector2 = v2.getVector();

		for (String token : vector1.keySet()) {
			
			if(vector2.containsKey(token)){
				Wn += vector1.get(token) * vector2.get(token);
			}
			w1 += Math.pow(vector1.get(token), 2);
		}

		for (String token : vector2.keySet()) {
			w2 += Math.pow(vector2.get(token), 2);
		}

		Wd = Math.sqrt(w1) * Math.sqrt(w2);
		
		if (Wd == 0.0) {
			return 0.0;
		}

		return Wn / Wd;
	}

	public Double getSimilarity(String title, String description) {

		Vector vectorTitle = FrequencyWordText.getFrequency(title);
		Vector vectorDescription = FrequencyWordText.getFrequency(description);

		return this.getScoreSimilarity(vectorTitle, vectorDescription);
	}

	public static void main(String[] args) {

		ScoreSimilarity s = new ScoreSimilarity();

		System.out.println("Result :"+s.getSimilarity(Questions.t1, Questions.d1));
		System.out.println("Result :"+s.getSimilarity("sim claro", "nao pode"));

	}
}
