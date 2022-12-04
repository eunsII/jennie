package com.githrd.jennie.sql;

public class BoardSQL {
	public final int SEL_BOARD_LIST			=	1001;
	public final int SEL_TOTAL_COUNT		=	1002;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
		case SEL_TOTAL_COUNT:
			buff.append("SELECT ");
			buff.append("	COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("	board ");
			buff.append("WHERE ");
			buff.append("	isshow = 'Y' ");
			break;
		case SEL_BOARD_LIST:
			buff.append("SELECT ");
			buff.append("    bno, id, title, wdate, click, cnt ");
			buff.append("FROM ");
			buff.append("    ( ");
			buff.append("        SELECT ");
			buff.append("            ROWNUM rno, bno, id, title, wdate, click, cnt ");
			buff.append("        FROM ");
			buff.append("            ( ");
			buff.append("                SELECT ");
			buff.append("                    bno, id, title, wdate, click, NVL(cnt, 0) cnt ");
			buff.append("                FROM ");
			buff.append("                    board b, member m, ");
			buff.append("                    ( ");
			buff.append("                        SELECT ");
			buff.append("                            fbno, COUNT(*) cnt ");
			buff.append("                        FROM ");
			buff.append("                            fileinfo ");
			buff.append("                        WHERE ");
			buff.append("                            isshow = 'Y' ");
			buff.append("                        GROUP BY ");
			buff.append("                            fbno ");
			buff.append("                    ) ");
			buff.append("                WHERE ");
			buff.append("                    bmno = mno ");
			buff.append("                    AND fbno(+) = bno ");
			buff.append("                    AND b.isshow = 'Y' ");
			buff.append("                    AND m.isshow = 'Y' ");
			buff.append("                ORDER BY ");
			buff.append("                    wdate DESC ");
			buff.append("            ) ");
			buff.append("    ) ");
			buff.append("WHERE ");
			buff.append("    rno BETWEEN ? AND ? ");
			break;
		}
		
		return buff.toString();
	}
}
