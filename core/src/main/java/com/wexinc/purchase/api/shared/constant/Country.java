package com.wexinc.purchase.api.shared.constant;

import java.util.Locale;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

/**
 * Enumeration responsible to list countris in the world.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public enum Country {

  /**
   * Entry in this enum to Afghanistan.
   *
   * @since 1.0.0
   */
  AFGHANISTAN,

  /**
   * Entry in this enum to Albania.
   *
   * @since 1.0.0
   */
  ALBANIA,

  /**
   * Entry in this enum to Algeria.
   *
   * @since 1.0.0
   */
  ALGERIA,

  /**
   * Entry in this enum to Angola.
   *
   * @since 1.0.0
   */
  ANGOLA,

  /**
   * Entry in this enum to Antigua.
   *
   * @since 1.0.0
   */
  ANTIGUA,

  /**
   * Entry in this enum to Antigua &#38; Barbuda.
   *
   * @since 1.0.0
   */
  ANTIGUA_AND_BARBUDA,

  /**
   * Entry in this enum to Argentina.
   *
   * @since 1.0.0
   */
  ARGENTINA,

  /**
   * Entry in this enum to Armenia.
   *
   * @since 1.0.0
   */
  ARMENIA,

  /**
   * Entry in this enum to Australia.
   *
   * @since 1.0.0
   */
  AUSTRALIA,

  /**
   * Entry in this enum to Austria.
   *
   * @since 1.0.0
   */
  AUSTRIA,

  /**
   * Entry in this enum to Azerbaidjan.
   *
   * @since 1.0.0
   */
  AZERBAIDJAN,

  /**
   * Entry in this enum to Azerbaijan.
   *
   * @since 1.0.0
   */
  AZERBAIJAN,

  /**
   * Entry in this enum to Bahamas.
   *
   * @since 1.0.0
   */
  BAHAMAS,

  /**
   * Entry in this enum to Bahrain.
   *
   * @since 1.0.0
   */
  BAHRAIN,

  /**
   * Entry in this enum to Bangladesh.
   *
   * @since 1.0.0
   */
  BANGLADESH,

  /**
   * Entry in this enum to Barbados.
   *
   * @since 1.0.0
   */
  BARBADOS,

  /**
   * Entry in this enum to Belarus.
   *
   * @since 1.0.0
   */
  BELARUS,

  /**
   * Entry in this enum to Belaruse.
   *
   * @since 1.0.0
   */
  BELARUSE,

  /**
   * Entry in this enum to Belgium.
   *
   * @since 1.0.0
   */
  BELGIUM,

  /**
   * Entry in this enum to Belize.
   *
   * @since 1.0.0
   */
  BELIZE,

  /**
   * Entry in this enum to Benin.
   *
   * @since 1.0.0
   */
  BENIN,

  /**
   * Entry in this enum to Bermuda.
   *
   * @since 1.0.0
   */
  BERMUDA,

  /**
   * Entry in this enum to Bolivia.
   *
   * @since 1.0.0
   */
  BOLIVIA,

  /**
   * Entry in this enum to Bosnia.
   *
   * @since 1.0.0
   */
  BOSNIA,

  /**
   * Entry in this enum to Botswana.
   *
   * @since 1.0.0
   */
  BOTSWANA,

  /**
   * Entry in this enum to Brazil.
   *
   * @since 1.0.0
   */
  BRAZIL,

  /**
   * Entry in this enum to Brunei.
   *
   * @since 1.0.0
   */
  BRUNEI,

  /**
   * Entry in this enum to Bulgaria.
   *
   * @since 1.0.0
   */
  BULGARIA,

  /**
   * Entry in this enum to Burkina Faso.
   *
   * @since 1.0.0
   */
  BURKINA_FASO,

  /**
   * Entry in this enum to Burma.
   *
   * @since 1.0.0
   */
  BURMA,

  /**
   * Entry in this enum to Burundi.
   *
   * @since 1.0.0
   */
  BURUNDI,

  /**
   * Entry in this enum to Cambodia.
   *
   * @since 1.0.0
   */
  CAMBODIA,

  /**
   * Entry in this enum to Cambodia Khmer.
   *
   * @since 1.0.0
   */
  CAMBODIA_KHMER,

  /**
   * Entry in this enum to Cameroon.
   *
   * @since 1.0.0
   */
  CAMEROON,

  /**
   * Entry in this enum to Canada.
   *
   * @since 1.0.0
   */
  CANADA,

  /**
   * Entry in this enum to Cape Verde.
   *
   * @since 1.0.0
   */
  CAPE_VERDE,

  /**
   * Entry in this enum to Cayman Island.
   *
   * @since 1.0.0
   */
  CAYMAN_ISLAND,

  /**
   * Entry in this enum to Cayman Islands.
   *
   * @since 1.0.0
   */
  CAYMAN_ISLANDS,

  /**
   * Entry in this enum to Central African Republic.
   *
   * @since 1.0.0
   */
  CENTRAL_AFRICAN_REP,

  /**
   * Entry in this enum to Central African Republic.
   *
   * @since 1.0.0
   */
  CENTRAL_AFRICAN_REPUBLIC,

  /**
   * Entry in this enum to Chad.
   *
   * @since 1.0.0
   */
  CHAD,

  /**
   * Entry in this enum to Chile.
   *
   * @since 1.0.0
   */
  CHILE,

  /**
   * Entry in this enum to China.
   *
   * @since 1.0.0
   */
  CHINA,

  /**
   * Entry in this enum to Colombia.
   *
   * @since 1.0.0
   */
  COLOMBIA,

  /**
   * Entry in this enum to Comoros.
   *
   * @since 1.0.0
   */
  COMOROS,

  /**
   * Entry in this enum to Congo.
   *
   * @since 1.0.0
   */
  CONGO,

  /**
   * Entry in this enum to Congo Democratic Republic.
   *
   * @since 1.0.0
   */
  CONGO_DEM_REP,

  /**
   * Entry in this enum to Costa Rica.
   *
   * @since 1.0.0
   */
  COSTA_RICA,

  /**
   * Entry in this enum to Cote D'Ivoire.
   *
   * @since 1.0.0
   */
  COTE_DIVOIRE,

  /**
   * Entry in this enum to Croatia.
   *
   * @since 1.0.0
   */
  CROATIA,

  /**
   * Entry in this enum to Cross Border.
   *
   * @since 1.0.0
   */
  CROSS_BORDER,

  /**
   * Entry in this enum to Cuba.
   *
   * @since 1.0.0
   */
  CUBA,

  /**
   * Entry in this enum to Cyprus.
   *
   * @since 1.0.0
   */
  CYPRUS,

  /**
   * Entry in this enum to Czech.
   *
   * @since 1.0.0
   */
  CZECH,

  /**
   * Entry in this enum to Czech Republic.
   *
   * @since 1.0.0
   */
  CZECH_REPUBLIC,

  /**
   * Entry in this enum to Democratic Republic of Congo.
   *
   * @since 1.0.0
   */
  DEM_REP_OF_CONGO,

  /**
   * Entry in this enum to Democratic Republic of Congo.
   *
   * @since 1.0.0
   */
  DEMOCRATIC_REPUBLIC_OF_CONGO,

  /**
   * Entry in this enum to Denmark.
   *
   * @since 1.0.0
   */
  DENMARK,

  /**
   * Entry in this enum to Djibouti.
   *
   * @since 1.0.0
   */
  DJIBOUTI,

  /**
   * Entry in this enum to Dominican Republic.
   *
   * @since 1.0.0
   */
  DOMINICAN_REPUBLIC,

  /**
   * Entry in this enum to Ecuador.
   *
   * @since 1.0.0
   */
  ECUADOR,

  /**
   * Entry in this enum to Egypt.
   *
   * @since 1.0.0
   */
  EGYPT,

  /**
   * Entry in this enum to El Salvador.
   *
   * @since 1.0.0
   */
  EL_SALVADOR,

  /**
   * Entry in this enum to Equatorial Guinea.
   *
   * @since 1.0.0
   */
  EQUATORIAL_GUINEA,

  /**
   * Entry in this enum to Eritrea.
   *
   * @since 1.0.0
   */
  ERITREA,

  /**
   * Entry in this enum to Estonia.
   *
   * @since 1.0.0
   */
  ESTONIA,

  /**
   * Entry in this enum to Eswatini.
   *
   * @since 1.0.0
   */
  ESWATINI,

  /**
   * Entry in this enum to Ethiopia.
   *
   * @since 1.0.0
   */
  ETHIOPIA,

  /**
   * Entry in this enum to Euro Zone.
   *
   * @since 1.0.0
   */
  EURO_ZONE,

  /**
   * Entry in this enum to Fiji.
   *
   * @since 1.0.0
   */
  FIJI,

  /**
   * Entry in this enum to Finland.
   *
   * @since 1.0.0
   */
  FINLAND,

  /**
   * Entry in this enum to France.
   *
   * @since 1.0.0
   */
  FRANCE,

  /**
   * Entry in this enum to Gabon.
   *
   * @since 1.0.0
   */
  GABON,

  /**
   * Entry in this enum to Gambia.
   *
   * @since 1.0.0
   */
  GAMBIA,

  /**
   * Entry in this enum to Georgia.
   *
   * @since 1.0.0
   */
  GEORGIA,

  /**
   * Entry in this enum to Germany.
   *
   * @since 1.0.0
   */
  GERMANY,

  /**
   * Entry in this enum to Germany FRG.
   *
   * @since 1.0.0
   */
  GERMANY_FRG,

  /**
   * Entry in this enum to Ghana.
   *
   * @since 1.0.0
   */
  GHANA,

  /**
   * Entry in this enum to Greece.
   *
   * @since 1.0.0
   */
  GREECE,

  /**
   * Entry in this enum to Grenada.
   *
   * @since 1.0.0
   */
  GRENADA,

  /**
   * Entry in this enum to Guatemala.
   *
   * @since 1.0.0
   */
  GUATEMALA,

  /**
   * Entry in this enum to Guinea.
   *
   * @since 1.0.0
   */
  GUINEA,

  /**
   * Entry in this enum to Guinea Bissau.
   *
   * @since 1.0.0
   */
  GUINEA_BISSAU,

  /**
   * Entry in this enum to Guyana.
   *
   * @since 1.0.0
   */
  GUYANA,

  /**
   * Entry in this enum to Haiti.
   *
   * @since 1.0.0
   */
  HAITI,

  /**
   * Entry in this enum to Honduras.
   *
   * @since 1.0.0
   */
  HONDURAS,

  /**
   * Entry in this enum to Hong Kong.
   *
   * @since 1.0.0
   */
  HONG_KONG,

  /**
   * Entry in this enum to Hungary.
   *
   * @since 1.0.0
   */
  HUNGARY,

  /**
   * Entry in this enum to Iceland.
   *
   * @since 1.0.0
   */
  ICELAND,

  /**
   * Entry in this enum to India.
   *
   * @since 1.0.0
   */
  INDIA,

  /**
   * Entry in this enum to Indonesia.
   *
   * @since 1.0.0
   */
  INDONESIA,

  /**
   * Entry in this enum to Iran.
   *
   * @since 1.0.0
   */
  IRAN,

  /**
   * Entry in this enum to Iraq.
   *
   * @since 1.0.0
   */
  IRAQ,

  /**
   * Entry in this enum to Ireland.
   *
   * @since 1.0.0
   */
  IRELAND,

  /**
   * Entry in this enum to Israel.
   *
   * @since 1.0.0
   */
  ISRAEL,

  /**
   * Entry in this enum to Italy.
   *
   * @since 1.0.0
   */
  ITALY,

  /**
   * Entry in this enum to Jamaica.
   *
   * @since 1.0.0
   */
  JAMAICA,

  /**
   * Entry in this enum to Japan.
   *
   * @since 1.0.0
   */
  JAPAN,

  /**
   * Entry in this enum to Jerusalem.
   *
   * @since 1.0.0
   */
  JERUSALEM,

  /**
   * Entry in this enum to Jordan.
   *
   * @since 1.0.0
   */
  JORDAN,

  /**
   * Entry in this enum to Kazakhstan.
   *
   * @since 1.0.0
   */
  KAZAKHSTAN,

  /**
   * Entry in this enum to Kenya.
   *
   * @since 1.0.0
   */
  KENYA,

  /**
   * Entry in this enum to Korea.
   *
   * @since 1.0.0
   */
  KOREA,

  /**
   * Entry in this enum to Kosovo.
   *
   * @since 1.0.0
   */
  KOSOVO,

  /**
   * Entry in this enum to Kuwait.
   *
   * @since 1.0.0
   */
  KUWAIT,

  /**
   * Entry in this enum to Kyrgyzstan.
   *
   * @since 1.0.0
   */
  KYRGYZSTAN,

  /**
   * Entry in this enum to Laos.
   *
   * @since 1.0.0
   */
  LAOS,

  /**
   * Entry in this enum to Latvia.
   *
   * @since 1.0.0
   */
  LATVIA,

  /**
   * Entry in this enum to Lebanon.
   *
   * @since 1.0.0
   */
  LEBANON,

  /**
   * Entry in this enum to Lesotho.
   *
   * @since 1.0.0
   */
  LESOTHO,

  /**
   * Entry in this enum to Liberia.
   *
   * @since 1.0.0
   */
  LIBERIA,

  /**
   * Entry in this enum to Libya.
   *
   * @since 1.0.0
   */
  LIBYA,

  /**
   * Entry in this enum to Lithuania.
   *
   * @since 1.0.0
   */
  LITHUANIA,

  /**
   * Entry in this enum to Lithuanian.
   *
   * @since 1.0.0
   */
  LITHUANIAN,

  /**
   * Entry in this enum to Luxembourg.
   *
   * @since 1.0.0
   */
  LUXEMBOURG,

  /**
   * Entry in this enum to Macao.
   *
   * @since 1.0.0
   */
  MACAO,

  /**
   * Entry in this enum to Macedonia Fyrom.
   *
   * @since 1.0.0
   */
  MACEDONIA_FYROM,

  /**
   * Entry in this enum to Madagascar.
   *
   * @since 1.0.0
   */
  MADAGASCAR,

  /**
   * Entry in this enum to Malawi.
   *
   * @since 1.0.0
   */
  MALAWI,

  /**
   * Entry in this enum to Malaysia.
   *
   * @since 1.0.0
   */
  MALAYSIA,

  /**
   * Entry in this enum to Maldives.
   *
   * @since 1.0.0
   */
  MALDIVES,

  /**
   * Entry in this enum to Mali.
   *
   * @since 1.0.0
   */
  MALI,

  /**
   * Entry in this enum to Malta.
   *
   * @since 1.0.0
   */
  MALTA,

  /**
   * Entry in this enum to Maltese.
   *
   * @since 1.0.0
   */
  MALTESE,

  /**
   * Entry in this enum to Marshall Islands.
   *
   * @since 1.0.0
   */
  MARSHALL_ISLANDS,

  /**
   * Entry in this enum to Marshalls Islands.
   *
   * @since 1.0.0
   */
  MARSHALLS_ISLANDS,

  /**
   * Entry in this enum to Martinique.
   *
   * @since 1.0.0
   */
  MARTINIQUE,

  /**
   * Entry in this enum to Mauritania.
   *
   * @since 1.0.0
   */
  MAURITANIA,

  /**
   * Entry in this enum to Mauritius.
   *
   * @since 1.0.0
   */
  MAURITIUS,

  /**
   * Entry in this enum to Mexico.
   *
   * @since 1.0.0
   */
  MEXICO,

  /**
   * Entry in this enum to Micronesia.
   *
   * @since 1.0.0
   */
  MICRONESIA,

  /**
   * Entry in this enum to Moldova.
   *
   * @since 1.0.0
   */
  MOLDOVA,

  /**
   * Entry in this enum to Mongolia.
   *
   * @since 1.0.0
   */
  MONGOLIA,

  /**
   * Entry in this enum to Montenegro.
   *
   * @since 1.0.0
   */
  MONTENEGRO,

  /**
   * Entry in this enum to Morocco.
   *
   * @since 1.0.0
   */
  MOROCCO,

  /**
   * Entry in this enum to Mozambique.
   *
   * @since 1.0.0
   */
  MOZAMBIQUE,

  /**
   * Entry in this enum to Myanmar.
   *
   * @since 1.0.0
   */
  MYANMAR,

  /**
   * Entry in this enum to Nambia.
   *
   * @since 1.0.0
   */
  NAMBIA,

  /**
   * Entry in this enum to Namibia.
   *
   * @since 1.0.0
   */
  NAMIBIA,

  /**
   * Entry in this enum to Nepal.
   *
   * @since 1.0.0
   */
  NEPAL,

  /**
   * Entry in this enum to Netherland.
   *
   * @since 1.0.0
   */
  NETHERLAND,

  /**
   * Entry in this enum to Netherlands.
   *
   * @since 1.0.0
   */
  NETHERLANDS,

  /**
   * Entry in this enum to Netherlands Antilles.
   *
   * @since 1.0.0
   */
  NETHERLANDS_ANTILLES,

  /**
   * Entry in this enum to New Zealand.
   *
   * @since 1.0.0
   */
  NEW_ZEALAND,

  /**
   * Entry in this enum to Nicaragua.
   *
   * @since 1.0.0
   */
  NICARAGUA,

  /**
   * Entry in this enum to Niger.
   *
   * @since 1.0.0
   */
  NIGER,

  /**
   * Entry in this enum to Nigeria.
   *
   * @since 1.0.0
   */
  NIGERIA,

  /**
   * Entry in this enum to Norway.
   *
   * @since 1.0.0
   */
  NORWAY,

  /**
   * Entry in this enum to Oman.
   *
   * @since 1.0.0
   */
  OMAN,

  /**
   * Entry in this enum to Pakistan.
   *
   * @since 1.0.0
   */
  PAKISTAN,

  /**
   * Entry in this enum to Palau.
   *
   * @since 1.0.0
   */
  PALAU,

  /**
   * Entry in this enum to Panama.
   *
   * @since 1.0.0
   */
  PANAMA,

  /**
   * Entry in this enum to Papua New Guinea.
   *
   * @since 1.0.0
   */
  PAPUA_NEW_GUINEA,

  /**
   * Entry in this enum to Paraguay.
   *
   * @since 1.0.0
   */
  PARAGUAY,

  /**
   * Entry in this enum to Peru.
   *
   * @since 1.0.0
   */
  PERU,

  /**
   * Entry in this enum to Philippines.
   *
   * @since 1.0.0
   */
  PHILIPPINES,

  /**
   * Entry in this enum to Poland.
   *
   * @since 1.0.0
   */
  POLAND,

  /**
   * Entry in this enum to Portugal.
   *
   * @since 1.0.0
   */
  PORTUGAL,

  /**
   * Entry in this enum to Qatar.
   *
   * @since 1.0.0
   */
  QATAR,

  /**
   * Entry in this enum to Republic of Macedonia.
   *
   * @since 1.0.0
   */
  REP_OF_N_MACEDONIA,

  /**
   * Entry in this enum to Republic of Palau.
   *
   * @since 1.0.0
   */
  REPUBLIC_OF_PALAU,

  /**
   * Entry in this enum to Romania.
   *
   * @since 1.0.0
   */
  ROMANIA,

  /**
   * Entry in this enum to Russia.
   *
   * @since 1.0.0
   */
  RUSSIA,

  /**
   * Entry in this enum to Rwanda.
   *
   * @since 1.0.0
   */
  RWANDA,

  /**
   * Entry in this enum to Sao Tome &#38; Principe.
   *
   * @since 1.0.0
   */
  SAO_TOME_AND_PRINCIPE,

  /**
   * Entry in this enum to Saudi Aarabia.
   *
   * @since 1.0.0
   */
  SAUDI_ARABIA,

  /**
   * Entry in this enum to Senegal.
   *
   * @since 1.0.0
   */
  SENEGAL,

  /**
   * Entry in this enum to Serbia.
   *
   * @since 1.0.0
   */
  SERBIA,

  /**
   * Entry in this enum to Seychelles.
   *
   * @since 1.0.0
   */
  SEYCHELLES,

  /**
   * Entry in this enum to Sierra Leone.
   *
   * @since 1.0.0
   */
  SIERRA_LEONE,

  /**
   * Entry in this enum to Singapore.
   *
   * @since 1.0.0
   */
  SINGAPORE,

  /**
   * Entry in this enum to Slovak.
   *
   * @since 1.0.0
   */
  SLOVAK,

  /**
   * Entry in this enum to Slovak Republic.
   *
   * @since 1.0.0
   */
  SLOVAK_REPUBLIC,

  /**
   * Entry in this enum to Slovakia.
   *
   * @since 1.0.0
   */
  SLOVAKIA,

  /**
   * Entry in this enum to Slovenia.
   *
   * @since 1.0.0
   */
  SLOVENIA,

  /**
   * Entry in this enum to Solomon Islands.
   *
   * @since 1.0.0
   */
  SOLOMON_ISLANDS,

  /**
   * Entry in this enum to Somali.
   *
   * @since 1.0.0
   */
  SOMALI,

  /**
   * Entry in this enum to South.
   *
   * @since 1.0.0
   */
  SOUTH,

  /**
   * Entry in this enum to South Africa.
   *
   * @since 1.0.0
   */
  SOUTH_AFRICA,

  /**
   * Entry in this enum to South Sudan.
   *
   * @since 1.0.0
   */
  SOUTH_SUDAN,

  /**
   * Entry in this enum to South Sudanese.
   *
   * @since 1.0.0
   */
  SOUTH_SUDANESE,

  /**
   * Entry in this enum to Spain.
   *
   * @since 1.0.0
   */
  SPAIN,

  /**
   * Entry in this enum to Sri Lanka.
   *
   * @since 1.0.0
   */
  SRI_LANKA,

  /**
   * Entry in this enum to Saint Lucia.
   *
   * @since 1.0.0
   */
  ST_LUCIA,

  /**
   * Entry in this enum to Sudan.
   *
   * @since 1.0.0
   */
  SUDAN,

  /**
   * Entry in this enum to Suriname.
   *
   * @since 1.0.0
   */
  SURINAME,

  /**
   * Entry in this enum to Swaziland.
   *
   * @since 1.0.0
   */
  SWAZILAND,

  /**
   * Entry in this enum to Sweden.
   *
   * @since 1.0.0
   */
  SWEDEN,

  /**
   * Entry in this enum to Switzerland.
   *
   * @since 1.0.0
   */
  SWITZERLAND,

  /**
   * Entry in this enum to Syria.
   *
   * @since 1.0.0
   */
  SYRIA,

  /**
   * Entry in this enum to Taiwan.
   *
   * @since 1.0.0
   */
  TAIWAN,

  /**
   * Entry in this enum to Tajikistan.
   *
   * @since 1.0.0
   */
  TAJIKISTAN,

  /**
   * Entry in this enum to Tanzania.
   *
   * @since 1.0.0
   */
  TANZANIA,

  /**
   * Entry in this enum to Thailand.
   *
   * @since 1.0.0
   */
  THAILAND,

  /**
   * Entry in this enum to Timor.
   *
   * @since 1.0.0
   */
  TIMOR,

  /**
   * Entry in this enum to Togo.
   *
   * @since 1.0.0
   */
  TOGO,

  /**
   * Entry in this enum to Tonga.
   *
   * @since 1.0.0
   */
  TONGA,

  /**
   * Entry in this enum to Trinidad &#38; Tobago.
   *
   * @since 1.0.0
   */
  TRINIDAD_AND_TOBAGO,

  /**
   * Entry in this enum to Tunisia.
   *
   * @since 1.0.0
   */
  TUNISIA,

  /**
   * Entry in this enum to Turkey.
   *
   * @since 1.0.0
   */
  TURKEY,

  /**
   * Entry in this enum to Turkmenistan.
   *
   * @since 1.0.0
   */
  TURKMENISTAN,

  /**
   * Entry in this enum to Uganda.
   *
   * @since 1.0.0
   */
  UGANDA,

  /**
   * Entry in this enum to Ukraine.
   *
   * @since 1.0.0
   */
  UKRAINE,

  /**
   * Entry in this enum to United Arab Emirates.
   *
   * @since 1.0.0
   */
  UNITED_ARAB_EMIRATES,

  /**
   * Entry in this enum to United Kingdom.
   *
   * @since 1.0.0
   */
  UNITED_KINGDOM,

  /**
   * Entry in this enum to Uruguay.
   *
   * @since 1.0.0
   */
  URUGUAY,

  /**
   * Entry in this enum to Uzbekistan.
   *
   * @since 1.0.0
   */
  UZBEKISTAN,

  /**
   * Entry in this enum to Vanuatu.
   *
   * @since 1.0.0
   */
  VANUATU,

  /**
   * Entry in this enum to Venezuela.
   *
   * @since 1.0.0
   */
  VENEZUELA,

  /**
   * Entry in this enum to Vietnam.
   *
   * @since 1.0.0
   */
  VIETNAM,

  /**
   * Entry in this enum to Western Samoa.
   *
   * @since 1.0.0
   */
  WESTERN_SAMOA,

  /**
   * Entry in this enum to Yemen.
   *
   * @since 1.0.0
   */
  YEMEN,

  /**
   * Entry in this enum to Zambia.
   *
   * @since 1.0.0
   */
  ZAMBIA,

  /**
   * Entry in this enum to Zimbabwe.
   *
   * @since 1.0.0
   */
  ZIMBABWE;

  /**
   * Method responsible to return the {@link #name()} of the enum with only its first letters of
   * each word capitalized.
   *
   * @return The {@link #name()} of the enum with only its first letters of each word capitalized.
   * @since 1.0.0
   * @see StringUtils#capitalize(String)
   */
  public String getCapitalizedName() {
    return String.join(
        " ",
        Stream.of(
                ConstantsCore.PATTERN_UNDERSCORE.split(
                    this.name().toLowerCase(Locale.getDefault())))
            .map(StringUtils::capitalize)
            .toList());
  }
}
