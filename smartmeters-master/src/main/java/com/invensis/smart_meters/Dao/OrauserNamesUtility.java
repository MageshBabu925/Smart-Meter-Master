package com.invensis.smart_meters.Dao;


public class OrauserNamesUtility {

	public static String getDBSchemaDetails(String erocode) throws Exception {
		String username = null;
		if (erocode.equals("211"))
			username = "KDPKDP";
		else if (erocode.equals("212"))
			username = "KDPKDR";
		else if (erocode.equals("221"))
			username = "KDPJAM";
		else if (erocode.equals("222"))
			username = "KDPPRO";
		else if (erocode.equals("223"))
			username = "KDPYER";
		else if (erocode.equals("232"))
			username = "KDPRAJ";
		else if (erocode.equals("233"))
			username = "KDPKOD";
		else if (erocode.equals("234"))
			username = "KDPONM";
		else if (erocode.equals("241"))
			username = "KDPRCY";
		else if (erocode.equals("242"))
			username = "KDPLRP";
		else if (erocode.equals("251"))
			username = "KDPPUL";
		else if (erocode.equals("252"))
			username = "KDPVMP";
		else if (erocode.equals("261"))
			username = "KDPMYD";
		else if (erocode.equals("262"))
			username = "KDPBAD";
		else if (erocode.equals("263"))
			username = "KDPPML";
		else if (erocode.equals("311"))
			username = "NELGDR";
		else if (erocode.equals("312"))
			username = "NELKOT";
		else if (erocode.equals("321"))
			username = "NELKAV";
		else if (erocode.equals("322"))
			username = "NELKOV";
		else if (erocode.equals("371"))
			username = "NELKOV";
		else if (erocode.equals("323"))
			username = "NELKAL";
		else if (erocode.equals("324"))
			username = "NELBUC";
		else if (erocode.equals("372"))
			username = "NELBUC";
		// else if (erocode.equals("325"))
		// username = "NELNRP";
		else if (erocode.equals("331"))
			username = "NELNT1";
		else if (erocode.equals("332"))
			username = "NELNT2";
		else if (erocode.equals("341"))
			username = "NELAKR";
		else if (erocode.equals("342"))
			username = "NELUDG";
		else if (erocode.equals("351"))
			username = "NELNER";
		else if (erocode.equals("352"))
			username = "NELPDK";
		// else if (erocode.equals("353"))
		// username = "NELIPT";
		else if (erocode.equals("354"))
			username = "NELMTK";
		else if (erocode.equals("361"))
			username = "NELNPT";
		else if (erocode.equals("362"))
			username = "NELVGR";
		else if (erocode.equals("363"))
			username = "NELSPT";
		else if (erocode.equals("511"))
			username = "TIRCHI";
		else if (erocode.equals("521"))
			username = "TIRMAD";
		else if (erocode.equals("581"))
			username = "TIRPUN";
		else if (erocode.equals("524"))
			username = "TIRMKC";
		else if (erocode.equals("531"))
			username = "TIRNAG";
		else if (erocode.equals("532"))
			username = "TIRPUT";
		else if (erocode.equals("533"))
			username = "TIRNGP";
		else if (erocode.equals("534"))
			username = "TIRKVN";
		else if (erocode.equals("541"))
			username = "TIRPAK";
		else if (erocode.equals("542"))
			username = "TIRCGR";
		else if (erocode.equals("543"))
			username = "TIRSKH";
		else if (erocode.equals("552"))
			username = "TIRTP1";
		else if (erocode.equals("553"))
			username = "TIRTP2";
		else if (erocode.equals("554"))
			username = "TIRRGT";
		else if (erocode.equals("561"))
			username = "TIRCTR";
		else if (erocode.equals("582"))
			username = "TIRPAL";
		else if (erocode.equals("563"))
			username = "TIRPAP";
		else if (erocode.equals("571"))
			username = "TIRPIL";
		else if (erocode.equals("572"))
			username = "TIRVAY";
		else if (erocode.equals("573"))
			username = "TIRKLK";
		else if (erocode.equals("583"))
			username = "TIRSOD";
		else if (erocode.equals("711"))
			username = "ANAAPT";
		else if (erocode.equals("712"))
			username = "ANAAPE";
		else if (erocode.equals("761"))
			username = "ANAAPW";
		else if (erocode.equals("762"))
			username = "ANADHA";
		else if (erocode.equals("721"))
			username = "ANAGOO";
		else if (erocode.equals("722"))
			username = "ANAGUN";
		else if (erocode.equals("723"))
			username = "ANATAD";
		else if (erocode.equals("724"))
			username = "ANATCO";
		else if (erocode.equals("731"))
			username = "ANAHIN";
		else if (erocode.equals("732"))
			username = "ANAMAD";
		else if (erocode.equals("733"))
			username = "ANAPEN";
		else if (erocode.equals("741"))
			username = "ANAKAT";
		else if (erocode.equals("742"))
			username = "ANAKAR";
		else if (erocode.equals("743"))
			username = "ANAPUT";
		else if (erocode.equals("751"))
			username = "ANAKAL";
		else if (erocode.equals("752"))
			username = "ANARAY";
		else if (erocode.equals("753"))
			username = "ANAURA";
		else if (erocode.equals("811"))
			username = "KURADO";
		else if (erocode.equals("812"))
			username = "KURPAT";
		else if (erocode.equals("813"))
			username = "KURYEM";
		else if (erocode.equals("861"))
			username = "KURYEM";
		else if (erocode.equals("821"))
			username = "KURBAN";
		else if (erocode.equals("822"))
			username = "KURDHO";
		else if (erocode.equals("831"))
			username = "KURKUT";
		else if (erocode.equals("832"))
			username = "KURKUR";
		else if (erocode.equals("833"))
			username = "KURNDK";
		else if (erocode.equals("853"))
			username = "KURNDK";
		else if (erocode.equals("841"))
			username = "KURALL";
		else if (erocode.equals("842"))
			username = "KURBAN";
		else if (erocode.equals("843"))
			username = "KURNAN";
		else if (erocode.equals("852"))
			username = "KURKUR";
		else
			throw new Exception("Invalid Service Number, ERO Not found");
		return username;

	}

}