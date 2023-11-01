Cache management in Java refers to the process of storing frequently accessed data in a temporary storage area, known as a cache,
to improve application performance by reducing the need to fetch the data from the original source repeatedly. Caching helps in minimizing response times and conserving resources.

Annotations for Cache Management in Java:

@Cacheable: Used to indicate that a method's results should be cached. It specifies that the method's return value should be stored in the cache for future
invocations with the same arguments.

@CacheEvict: This annotation is used to trigger cache eviction, allowing you to remove specific entries or clear the entire cache for a method or class.

@CachePut: Specifies that a method should always be executed, and its result should be placed in the cache. It's often used for updating or refreshing cache entries.

@CacheConfig: Used at the class level to specify common cache-related settings for methods within the class. This annotation simplifies cache configuration.

These annotations are part of the Spring Framework's caching support and are often used in conjunction with cache providers like Ehcache, Redis, or Caffeine to
enhance application performance by efficiently managing cached data.



HashOperations is an interface in Spring Data Redis that provides a set of methods to perform operations on Redis hash data structures. Redis hashes are maps that store field-value
pairs, making them useful for representing structured data. The HashOperations interface simplifies the manipulation of these hash data structures in Redis.

Key Methods of HashOperations:

put(K key, HK hashKey, HV value): Adds a field-value pair to the hash associated with the given key.

get(K key, HK hashKey): Retrieves the value associated with the specified hash key from the hash identified by the key.

entries(K key): Returns all field-value pairs from the hash with the given key.

delete(K key, Object... hashKeys): Removes one or more hash keys and their associated values from the hash.

hasKey(K key, Object hashKey): Checks whether a hash key exists within the hash.

keys(K key): Retrieves all field (hash key) names from the hash.

values(K key): Retrieves all values stored in the hash.

These methods enable you to interact with Redis hashes efficiently. You can use HashOperations to store, retrieve, and manipulate structured data in Redis hashes,
which is particularly useful when dealing with complex objects or data models. Spring Data Redis simplifies the integration of Redis data structures into Java applications,
and HashOperations is a vital component of this integration for working with Redis hash maps.

Example:
1. Create bean of hash operations
 @Bean
 public HashOperations<String, Long, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
    return redisTemplate.opsForHash();
 }

 2. Inject HashOperations
 private HashOperations<String, Long, Object> operations;

        //add: @CachePut(value = "users", key = "#user.id")
        //add: operations.putIfAbsent("users",user.getId(), user);
        //update: operations.put("users",user.getId())
        //get record: operations.get("users", user.getId())
        //get all records: operations.entries("users")
        //remove entry: operations.delete("users", user.getId()

