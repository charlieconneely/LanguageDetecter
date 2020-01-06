package ie.gmit.sw;

/**
 * @author Charlie Conneely
 * @version 1.0
 * @since 1.8
 * 
 * The LanguageEntry class represents each
 * n-gram or k-mer using the attributes kmer, 
 * frequency and rank
*/
public class LanguageEntry implements Comparable<LanguageEntry> {
	private int kmer;
	private int frequency;
	private int rank;

	/**
	 * Constructor for LanguageEntry
	 * 
	 * @param kmer
	 * @param frequency
	 */
	public LanguageEntry(int kmer, int frequency) {
		super();
		this.kmer = kmer;
		this.frequency = frequency;
	}

	/**
	 * Gets the kmer value
	 * 
	 * @return kmer value
	 */
	public int getKmer() {
		return kmer;
	}

	/**
	 * Sets the kmer value
	 * 
	 * @param kmer the value to be applied to this
	 * instance of the kmer variable
	 */
	public void setKmer(int kmer) {
		this.kmer = kmer;
	}

	/**
	 * Gets the frequency value
	 * 
	 * @return frequency value
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Sets the frequency value
	 * 
	 * @param frequency the value to be applied to this
	 * instance of the frequency variable
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * Gets the rank value
	 * 
	 * @return rank value
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * Sets the rank value
	 * 
	 * @param rank the value to be applied to this
	 * instance of the rank variable
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * Compare one kmer to another based on their 
	 * frequency
	 * 
	 * @param next represents the kmer to be compared
	 * to another kmer
	 * @return the int difference between the two frequencies
	 */
	public int compareTo(LanguageEntry next) {
		return - Integer.compare(frequency, next.getFrequency());
	}
	
	/**
	 * Presents the kmer, frequency and rank attributes in
	 * String format
	 * 
	 * @return all variables in String format
	 */
	public String toString() {
		return "[" + kmer + "/" + frequency + "/" + rank + "]";
	}
}