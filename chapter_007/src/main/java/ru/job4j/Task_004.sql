-- Retrieve in a single query:
-- names of all persons that are NOT in the company with id = 5
-- company name for each person

SELECT
  person.name  AS person_name,
  company.name AS company_name
FROM person
  LEFT OUTER JOIN company ON company.id = person.company_id
WHERE company_id != 5;

-- Select the name of the company with the maximum number of persons + number of persons in this company

SELECT
  company.name      AS company_name,
  COUNT(company_id) AS num_of_persons
FROM person
  LEFT OUTER JOIN company ON company.id = person.company_id
GROUP BY company.name
ORDER BY num_of_persons DESC
LIMIT 1;

