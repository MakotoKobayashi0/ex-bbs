select a.id as "a.id", a.name as "a.name", a.content as "a.content", 
c.id as "c.id", c.name as "c.name", c.content as "c.content", c.article_id as "c.article_id"
from articles a inner join comments c on a.id=c.article_id 
order by a.id desc;