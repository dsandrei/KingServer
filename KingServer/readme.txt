Design considerations for the SessionHelper and ScoreHelper:

1. Session key generation, session cleanup:
	- UUID.randomUUID() is thread-safe, however researching its complexity revealed that it is a performance bottleneck in some programs
	- the concurrent use of the same java.util.Random instance across threads may encounter contention and consequent poor performance
	- java.util.Random uses a linear congruential pseudo-random number generator, which does not guarantee too big of a period
	- an implementation of the Mersenne Twister algorithm was used, with a local source for ThreadLocalRandom that uses the Mersenne twister for number generation
	- Timer was considered, however for optimized concurrency the ScheduledThreadPoolExecutor insures a constant load instead of using an iterative cleanup task that 
	would hog resources when called

2. Session data structure:
	- ConcurrentHashMap has superior performance to CopyonWriteArraySet for the expected data volume
	- Collections.synchronizedSet loses out on performance in high-concurrency scenarios

3. Score data structure:
	- The first implementation attempt was made with a ConcurrentSkipListSet/ConcurrentSkipListMap with the key and the value mapping to the same reference to try 
	and solve the problem of "unique by user id - sorting by score". The problem with this implementation is that the contains function and the sort function
	both use the compare function of the ConcurrentSkipListSet, instead of the advertised behavior of the contains function using equals.
	- The implementation makes use of CopyOnWriteArrayList under the assumption that high scores are much more likely to be read then to be achieved(once the app
	passes the initial deployment stage) and taking into consideration the small set size and the iterator consistency
	
4. Filtering
	- All parameter validation is concentrated in the ParameterFilter, with the application returning status code 500 for any error
	- the GET parameters are ignored, since we don't need them

5. Unit testing
	- A multi-threaded implementation of the Junit test runner was acquired to be able to run concurrency tests
	
6. Additional testing
	- A JMeter script was created for limited performance testing