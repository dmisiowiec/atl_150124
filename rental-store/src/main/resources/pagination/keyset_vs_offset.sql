-- limit-offset pagination
SELECT *
FROM movies
ORDER BY id
LIMIT 2 OFFSET 2;

-- keyset-based pagination
SELECT *
FROM movies
WHERE id > 4
ORDER BY id
LIMIT 2;


