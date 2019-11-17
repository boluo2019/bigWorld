/**
	 * Write a SQL query to find all duplicate emails in a table named Person.
	 *
	 * +----+---------+
	 * | Id | Email   |
	 * +----+---------+
	 * | 1  | a@b.com |
	 * | 2  | c@d.com |
	 * | 3  | a@b.com |
	 * +----+---------+
	 * For example, your query should return the following for the above table:
	 *
	 * +---------+
	 * | Email   |
	 * +---------+
	 * | a@b.com |
	 * +---------+
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/duplicate-emails
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */


// 考察SQL语句的书写顺序和运行顺序


SELECT Email
FROM Person
GROUP BY Email
HAVING (COUNT(Email) > 1)
;



SELECT Email FROM
(
  SELECT Email, count(Email) as num
  FROM Person
  GROUP BY Email
) as statistic
WHERE num > 1
;

// mysql 书写顺序
select[distinct]
from
join（如left join）
on
where
group by
having
union
order by
limit

// mysql 运行顺序
from
on
join
where
group by
having
select
distinct
union
order by