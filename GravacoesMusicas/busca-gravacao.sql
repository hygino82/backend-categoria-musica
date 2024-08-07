select mc.card_name as Nome,mc.card_level as Nivel, mc.card_atk as Ataque, mc.card_def as Defesa, mcol.color_name as Cor, mt.type_name as Tipo, ma.atribute_name as Atributo
from monster_card mc
join monster_color mcol on mcol.color_id = mc.color_id
join monster_type mt on mt.type_id = mc.type_id
join monster_atribute ma on ma.atribute_id = mc.atribute_id;

