INSERT INTO public.films (name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id) VALUES ('Пчеловод', 'Адам Клэй живет в тихом пригороде, занимается разведением пчел и не распространяется о своей прошлой жизни. Пожилая соседка рада, что в их край приехал заинтересованный человек, улучшающий природу и экологию. Пасечник часто общается с милой миссис Паркер, которая однажды становится жертвой финансовых мошенников. Аферисты похищают деньги со всех счетов женщины, и после тяжелого удара пенсионерка сводит счеты с жизнью. Адам решает за нее отомстить.
', 2024, 1, 18, 146, 1) ON CONFLICT DO NOTHING;
INSERT INTO public.films (name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id) VALUES ('Плохие парни', 'Двое парней без документов берутся за простую работу - копать яму в пустыне, но когда их работодатели оказываются психопатами, остаться в живых становится самой сложной работой в их жизни.
', 2022, 2, 16, 110, 2) ON CONFLICT DO NOTHING;
INSERT INTO public.films (name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id) VALUES ('Из глубины', 'Самолет терпит крушение в Тихом океане. Затонувший авиалайнер висит на караю пропасти, а выжившие оказываются в западне воздушного кармана. Запас воздуха постепенно заканчивается, и между пассажирами начинается борьба за выживание.
', 2024, 5, 18, 90, 3) ON CONFLICT DO NOTHING;